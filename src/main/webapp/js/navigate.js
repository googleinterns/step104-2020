function goToPage(page, params) {
  const url = new URL(page, window.location.origin);
  window.location.href = `${url.toString()}?${params.toString()}`
}

