/**
 * Convert a single file-input element into a 'multiple' input list
 * Usage:
 *
 *   1. Create a file input element (no name)
 *      eg. <input type="file" id="first_file_element">
 *
 *   2. Create a DIV for the output to be written to
 *      eg. <div id="files_list"></div>
 *
 *   3. Instantiate a MultiSelector object, passing in the DIV and an (optional) maximum number of files
 *      eg. var multi_selector = new MultiSelector( document.getElementById( 'files_list' ), 3 );
 *
 *   4. Add the first element
 *      eg. multi_selector.addElement( document.getElementById( 'first_file_element' ) );
 */

//왼쪽메뉴
function goLeftMenu(url,leftMenu) {
    document.topnaviForm.url.value = url;
    document.topnaviForm.leftMenu.value = leftMenu;
    document.topnaviForm.submit();
}

//왼쪽메뉴
function goLeftMenu(url,leftMenu, openMenu) {
    document.topnaviForm.url.value = url;
    document.topnaviForm.leftMenu.value = leftMenu;
    document.topnaviForm.openMenu.value = openMenu;
    document.topnaviForm.submit();
}
