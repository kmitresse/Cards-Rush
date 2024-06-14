document.querySelectorAll('.modal-trigger').forEach(($el) => {
  $el.addEventListener('click', () => {
    const target = $el.dataset.target;
    const $target = document.querySelector(target);
    $target.classList.add('is-active');
  });
});

const closeModal = ($el) => $el.classList.remove('is-active');
const closeAllModals = () => (document.querySelectorAll('.modal') || []).forEach(($modal) => closeModal($modal));

// Add a click event on various child elements to close the parent modal
(document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
  const $target = $close.closest('.modal');
  $close.addEventListener('click', () => closeModal($target));
});

// Add a keyboard event to close all modals
document.addEventListener('keydown', (event) => {
  if (event.key === "Escape") closeAllModals();
});
