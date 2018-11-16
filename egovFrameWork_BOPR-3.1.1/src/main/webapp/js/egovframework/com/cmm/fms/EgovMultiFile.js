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

function MultiSelector( list_target, max ){

	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};
	
	/**
	 * Add a new file input element
	 */
	this.addElement = function( element ){

		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){

			// Element name -- what number am I?
			element.name = element.name + this.id++;
			// Add reference to this object
			element.multi_selector = this;
			
			// What to do when a file is selected
			element.onchange = function(){
				
				var isFileExist = true;

				var filePath = element.value.replace(/\\/gi, "/").split("/");
				var fileName = filePath[filePath.length-1];
				
				if(typeof(document.getElementById('fileListTable'))!='undefined' && document.getElementById('fileListTable')!=null){
					var spanArray = document.getElementById('fileListTable').getElementsByTagName('span');
					for ( var i = 0; i < spanArray.length; i++) {
						
						if(spanArray[i].innerText == fileName) {
							
							isFileExist = false;
						}
					}
				}
				
				if(typeof(document.getElementById('egovComFileList'))!='undefined' && document.getElementById('egovComFileList')!=null){
					var divArray = document.getElementById('egovComFileList').getElementsByTagName('div');
					for ( var i = 0; i < divArray.length; i++) {
					
						var divFilePath = divArray[i].innerText.replace(/\\/gi, "/").split("/");;
						var divFileName = divFilePath[divFilePath.length-1];
						if(divFileName == fileName) {
							
							isFileExist = false;
						}
					}
					
				}
				
				if(fileName == "") {
					
					isFileExist = false;
					return;
				}
				
				if(isFileExist) {
					
					// New file input
					var new_element = document.createElement( 'input' );
					new_element.type = 'file';
	
					// Add new element
					this.parentNode.insertBefore( new_element, this );
	
					// Apply 'update' to element
					this.multi_selector.addElement( new_element );
					
					// Update list
					this.multi_selector.addListRow( this );
					
					// Hide this: we can't use display:none because Safari doesn't like it
					this.style.position = 'absolute';
					this.style.left = '-1000px';
					this.style.top = '-1000px';
					this.style.display = 'none';
					this.style.visibility = 'hidden';
					this.style.width = '0';
					this.style.height = '0';
					this.style.overflow = 'hidden';
	
					if( this.max != -1 && this.count >= this.max ){
						element.disabled = true;
					};
					
					this.count++;
					
					new_element.onkeypress = function(){
						return false;
					};
					
				} else {
					
					alert("첨부파일 이름이 중복됩니다.");
					
					if(navigator.appName == "Microsoft Internet Explorer"){
						
						document.getElementById("file_upload_posbl").innerHTML = document.getElementById("file_upload_posbl").innerHTML;
					} else {
						
						element.value = "";
					}
				}
			};
			
			// If we've reached maximum number, disable input element
			if( this.max != -1 && this.count >= this.max ){
				element.disabled = true;
			};
			
			// File element counter
			this.count++;
			
			// Most recent element
			this.current_element = element;
			
		} else {
			// This can only be applied to file input elements!
			alert( 'Error: not a file input element' );
		};

	};

	/**
	 * Add a new row to the list of files
	 */
	this.addListRow = function( element ){

		// Row div
		var new_row = document.createElement( 'div' );
		
		// Delete button
		var new_span = document.createElement( 'span' );
		new_span.className = "bbsBtn";
		new_span.innerHTML = "Delete";

		// References
		new_row.element = element;

		// Delete function
		new_span.onclick = function() {
			
			// Remove element from form
			this.parentNode.element.parentNode.removeChild( this.parentNode.element );

			// Remove this row from the list
			this.parentNode.parentNode.removeChild( this.parentNode );

			// Decrement counter
			this.parentNode.element.multi_selector.count--;

			// Re-enable input element (if it's disabled)
			this.parentNode.element.multi_selector.current_element.disabled = false;

			//    which nixes your already queued uploads
			return false;
		};
		
		// Set row value
		new_row.innerHTML = element.value;
		
		// Add button
		new_row.appendChild( new_span );

		// Add it to the list
		this.list_target.appendChild( new_row );
	};

};
