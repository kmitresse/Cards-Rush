var cb = document.querySelectorAll(".close");
for (i = 0; i < cb.length; i++) {
  cb[i].addEventListener("click", function() {
    var dia = this.parentNode.parentNode; /* You need to update this part if you change level of close button */
    dia.style.display = "none";
  });
}

var mt = document.querySelectorAll(".modal-toggle");
for (i = 0; i < mt.length; i++) {
  mt[i].addEventListener("click", function() {
    var targetId = this.getAttribute("data-target");
    var target = document.querySelector(targetId);
    target.style.display = "block";
  });
}
