const table = document.querySelector('#pagination-players tbody');
const userId = document.querySelector('#user-id');
const url = new URL(window.location.href);
const contextPath = url.pathname.substring(0, url.pathname.indexOf("/", 1) + 1)
const languageSelector = document.getElementById('language-select');
const currentPage = document.querySelector('input#current-page');
const nbPages = document.querySelector('#nb-pages');
console.log("nbPages:" + nbPages.value);
let showLabel;
if (languageSelector.value === 'EN') {
  showLabel = 'Show';
} else {
  showLabel = 'Voir';
}

const paginationPrevious = document.querySelector('.pagination-previous');
const paginationNext = document.querySelector('.pagination-next');
const paginationCurrent = document.querySelector('.pagination-current');

function updatePagination(pageNumber) {
  document.querySelectorAll('.played-game-row').forEach(row => table.removeChild(row));
  const url = new URL(window.location.href);
  url.searchParams.set('userId', userId.value);
  url.searchParams.set('pageNumber', pageNumber-1);
  const method = 'PUT';
  fetch(url, {headers: {"Content-Type": "application/json"}, method})
    .then(res => res.json())
    .then(data => {
      for (let i = 0; i < 5; i++) {
        if (data[i] === undefined) {
          const row = document.createElement('tr');
          row.classList.add('played-game-row');
          row.innerHTML = `
           <td></td>
           <td></td>
           <td></td>
           <td></td>
`
          table.appendChild(row);
        } else {
          const row = document.createElement('tr');
          row.classList.add('played-game-row');
          row.innerHTML = `
         <td>${data[i].createdDate}</td>
         <td>${data[i].score}</td>
         <td>${data[i].winnerUsername}</td>
         <td><a href="${contextPath}game-statistics?id=${data[i].gameId}">${showLabel}</a></td>`
          table.appendChild(row);
        }
      }


      // data.forEach(player => {
      //   const row = document.createElement('tr');
      //   row.classList.add('played-game-row');
      //   row.innerHTML = `
      //      <td>${player.createdDate}</td>
      //      <td>${player.score}</td>
      //      <td>${player.winnerUsername}</td>
      //      <td><a href="${contextPath}game-statistics?id=${player.gameId}">${showLabel}</a></td>`
      //   table.appendChild(row);
      // });
  })
}

function updateButtons(oldPageNumber,newPageNumber) {
  console.log("old: " + oldPageNumber +", new: " + newPageNumber)

  if (oldPageNumber === "1") {
    console.log("previous - not disable")
    paginationPrevious.classList.remove('is-disable');
  }
  if (newPageNumber === "1") {
    console.log("previous - disable")
    paginationPrevious.classList.add('is-disable');
  }
  if (oldPageNumber === nbPages.value.toString()) {
    console.log("next - not disable")
    paginationNext.classList.remove('is-disable');
  }
  if (newPageNumber === nbPages.value.toString()) {
    console.log("next - disable")
    paginationNext.classList.add('is-disable');
  }
  paginationCurrent.textContent = `${newPageNumber}`;
  console.log(paginationCurrent)


}

updatePagination(currentPage.value);
updateButtons(currentPage.value, currentPage.value)


paginationPrevious.addEventListener('click', () => {
  const newPageNumber = (parseInt(currentPage.value) - 1).toString();
  const oldPageNumber = currentPage.value.toString();
  currentPage.value = newPageNumber;
  updatePagination(newPageNumber);
  updateButtons(oldPageNumber, newPageNumber);
})

paginationNext.addEventListener('click', () => {
  const newPageNumber = (parseInt(currentPage.value) + 1).toString();
  const oldPageNumber = currentPage.value.toString();
  currentPage.value = newPageNumber;
  updatePagination(newPageNumber);
  updateButtons(oldPageNumber, newPageNumber);
})
