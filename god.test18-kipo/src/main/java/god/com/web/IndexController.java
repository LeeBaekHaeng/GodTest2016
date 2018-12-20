package god.com.web;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.FileManageDAO;
import god.com.service.IndexVO;

@Controller
public class IndexController {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "FileManageDAO")
	private FileManageDAO fileManageDAO;

	@RequestMapping("/god/index.do")
	public String index(IndexVO indexVO, Model model) {

		egovLogger.debug("index");

		egovLogger.debug("indexVO=" + indexVO);
		egovLogger.debug("getParam1=" + indexVO.getParam1());
		egovLogger.debug("getParam1s=" + indexVO.getParam1s());

		model.addAttribute("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return "god/com/index";
	}

	/**
	 * http://localhost:8080/test18-kipo/god/blobToImage.do?atchFileId=FILE_000000000000061&fileSn=1
	 * 
	 * @param vo
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/god/blobToImage.do")
	public void blobToImage(FileVO vo, Model model, HttpServletResponse response) throws Exception {

		// response.setContentType(MediaType.IMAGE_GIF_VALUE);

		FileVO selectFileInf = fileManageDAO.selectFileInf(vo);

		egovLogger.debug("getFileCn2=" + selectFileInf.getFileCn2());

		ByteArrayInputStream input = new ByteArrayInputStream(selectFileInf.getFileCn2());
		OutputStream output = response.getOutputStream();
		int copy = IOUtils.copy(input, output);
		egovLogger.debug("copy=" + copy);

		output.write(selectFileInf.getFileCn2());
	}

}
