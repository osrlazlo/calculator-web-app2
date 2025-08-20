let outputDisplay = document.getElementById("output-display").shadowRoot;
outputDisplay.addEventListener("input", _ => {
    console.log("scroll")
    outputDisplay.scroll() = outputDisplay.scrollWidth;
})