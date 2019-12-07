// prepares the base64-encoded string and enabled button
function getBase64(file) {
var reader = new FileReader();
reader.readAsDataURL(file);

reader.onload = function () {
  document.uploadSegmentForm.base64Encoding.value = reader.result;
  document.uploadSegmentForm.uploadSegmentButton.disabled = false;
};
}

// When file is selected, stash base64 value in the encoding field.  
function handleFileSelect(evt) {
  var files = evt.target.files; 
  if (files[0].size > 1000000) {  // make as large or small as you need
    document.uploadSegmentForm.base64Encoding.value = "";
    alert("File size too large to use:" + files[0].size + " bytes");
  } else {
    getBase64(files[0]); // request the load (async)
  }
}