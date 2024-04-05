let cb = document.querySelectorAll(".close");
for (let i = 0; i < cb.length; i++) {
  cb[i].addEventListener("click", function() {
    const dia = this.parentNode.parentNode; /* You need to update this part if you change level of close button */
    dia.style.display = "none";
  });
}

let mt = document.querySelectorAll(".modal-toggle");
for (let i = 0; i < mt.length; i++) {
  mt[i].addEventListener("click", function() {
    const targetId = this.getAttribute("data-target");
    const target = document.querySelector(targetId);
    target.style.display = "block";
  });
}
