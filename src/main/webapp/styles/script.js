function copyCode(id) {
    const code = document.querySelector(`code[data-id='${id}']`)?.textContent?.trim();

    if (!code) {
        alert("No code to copy.");
        return;
    }

    navigator.clipboard.writeText(code)
        .then(() => {})
        .catch(err => alert("Failed to copy code."));
}

function downloadCode(id) {
    const codeElement = document.querySelector(`code[data-id='${id}']`);
    const preElement = document.querySelector(`pre[data-id='${id}']`);
    const code = codeElement?.textContent?.trim();
    const filename = preElement?.getAttribute('data-download-link') || `snippet-${id}.txt`;

    if (!code) {
        alert("No code to download.");
        return;
    }

    const blob = new Blob([code], { type: 'text/plain' });
    const url = URL.createObjectURL(blob);

    const anchor = document.createElement("a");
    anchor.href = url;
    anchor.download = filename;
    document.body.appendChild(anchor);
    anchor.click();
    document.body.removeChild(anchor);

    URL.revokeObjectURL(url);
}
