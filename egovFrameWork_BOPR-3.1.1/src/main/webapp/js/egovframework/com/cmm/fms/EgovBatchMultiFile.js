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
	var delCnt = 0;
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
		//if( element.tagName == 'INPUT' && element.type == 'file' ){
		if( element.type == 'file' ){

			// Element name -- what number am I?
			element.name = element.name + this.id++;
			//element.name = "egovComFileUploader";
			// Add reference to this object
			element.multi_selector = this;
			
			element.id = "egovComFileUploader"+this.id;

			// What to do when a file is selected
			element.onchange = function(){

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

				new_element.onkeypress = function(){
					return false;
				};

			};
			// If we've reached maximum number, disable input element
			if( this.max != -1 && this.count >= this.max ){
				element.disabled = true;
			};

			// File element counter
			this.count++;
			delCnt++;
			
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
		var new_row_button = document.createElement( 'input' );
		new_row_button.type = 'button';
		new_row_button.value = 'Delete';

		// Path Table
		var atchFlag = document.getElementById("atchType");
		var tBody = document.getElementById("attachInfo");
		var rows = tBody.rows;
		// References
		new_row.element = element;

		// Delete function
		new_row_button.onclick= function(){

			// Remove element from form
			this.parentNode.element.parentNode.removeChild( this.parentNode.element );

			// Remove this row from the list
			this.parentNode.parentNode.removeChild( this.parentNode );

			delCnt--;
			
			if(atchFlag.value=="UPT"){
				var totalCount = delCnt + parseInt(document.getElementById("batchDlbrtAtchSize").value);
				document.getElementById("atchCnt").value = totalCount;
				if(totalCount > 1){
					//tBody.removeChild(tBody.lastChild);
					tBody.deleteRow(rows.length-1);
				}
			}else if(atchFlag.value=="INS"){
				document.getElementById("atchCnt").value = delCnt;
				if(delCnt > 1){
					//tBody.removeChild(tBody.lastChild);
					tBody.deleteRow(rows.length-1);
				}
			}
			
			// Decrement counter
			this.parentNode.element.multi_selector.count--;
			
			this.count--;
			
			

			// Re-enable input element (if it's disabled)
			this.parentNode.element.multi_selector.current_element.disabled = false;

			//    which nixes your already queued uploads
			return false;
		};

		// Set row value
		new_row.innerHTML = element.value;

		// Add button
		new_row.appendChild( new_row_button );
		
		// Add Path
		if(atchFlag.value=="UPT"){
			var totalCount = this.count + parseInt(document.getElementById("batchDlbrtAtchSize").value);
			document.getElementById("atchCnt").value = totalCount;
			if(totalCount>2){
				//tBody.appendChild(rows[rows.length-1].cloneNode(true));
				var newRow = tBody.insertRow(rows.length);
				var newChild = document.createElement("newChild");
				newChild = document.getElementById("wdtbPathHead").cloneNode(true);
				newRow.appendChild(newChild);
				newChild = document.getElementById("wdtbPathBody").cloneNode(true);
				newRow.appendChild(newChild);
			}
		}else if(atchFlag.value=="INS"){
			document.getElementById("atchCnt").value = delCnt;
			if(this.count>2){
				//tBody.appendChild(rows[rows.length-1].cloneNode(true));
				var newRow = tBody.insertRow(rows.length);
				var newChild = document.createElement("newChild");
				newChild = document.getElementById("wdtbPathHead").cloneNode(true);
				newRow.appendChild(newChild);
				newChild = document.getElementById("wdtbPathBody").cloneNode(true);
				newRow.appendChild(newChild);
			}
		}
		

		// Add it to the list
		this.list_target.appendChild( new_row );
	};

};