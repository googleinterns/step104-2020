function goToPage(page, params) {
  const params = new URLSearchParams(`platform=android&releaseName=M76&sdkName=${sdkName}`);
  const url = new URL(page, window.location.origin);
  window.location.href = `${url.toString()}?${params.toString()}`
}

