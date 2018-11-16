<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Job 정보</th>
		</tr>
		<tr>
			<td>Job 아이디</td><td>${ jobInstances.id }</td> 
		</tr>
		<tr>
			<td>Job 이름</td><td>${ jobInstances.name }</td> 
		</tr>
		<tr>
			<td>Job 파라미터</td><td>${ jobInstances.parameters }</td> 
		</tr>
		<tr>
			<td>Job 시작시간</td><td>${ jobInstances.startTime }</td>
		</tr>
		<tr>
			<td>Job 실행 중 여부</td><td>${ jobInstances.isRunning }</td> 
		</tr>
		<tr>
			<td>Job 종료시간</td><td>${ jobInstances.endTime }</td>
		</tr>
		<tr>
			<td>종료시 상태(exitStatus)</td><td>${jobInstances.exitStatus}</td> 
		</tr>
		<tr>
			<th>Step 정보</th>
		</tr>
		<tr>
			<td>Step 아이디</td><td>${stepInfo.stepId }</td>
		</tr>
		<tr>
			<td>Step 이름</td><td>${stepInfo.stepName }</td>
		</tr>
		<tr>
			<td>Read Count</td><td>${stepInfo.readCount }</td>
		</tr>
		<tr>
			<td>Write Count</td><td>${stepInfo.writeCount }</td>
		</tr>
		<tr>
			<td>Read Skip Count</td><td>${stepInfo.readSkipCount }</td>
		</tr>
		<tr>
			<td>Process Skip Count</td><td>${stepInfo.processSkipCount }</td>
		</tr>
		<tr>
			<td>Write Skip Count</td><td>${stepInfo.writeSkipCount }</td>
		</tr>
		<tr>
			<td>Total Skip Count</td><td>${stepInfo.totalSkipCount}</td>
		</tr>
		<tr>
			<td>Commit Count</td><td>${stepInfo.commitCount }</td>
		</tr>
		<tr>
			<td>Rollback Count</td><td>${stepInfo.rollbackCount }</td>
		</tr>
		<tr>
			<td>종료시 상태(exitStatus)</td><td>${stepInfo.exitStatus }</td>
		</tr>
	</table>
</body>
</html>