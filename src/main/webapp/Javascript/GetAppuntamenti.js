document.addEventListener("DOMContentLoaded", async function () {

    const mese = await getMeseURL();
    const anno = await getAnnoURL();
    await getGiorno(mese, anno);

})

// fetch che chiamerà il controller/servlet -- ritornerà dei dati -- che ci faccio con quei dati ??
async function callServletAppuntamenti(anno, mese, giorno) {
    await fetch(`http://localhost:8080/CalendarioServlet/AppuntamentiFetch?anno=${anno}&mese=${mese}&giorno=${giorno}`, {
        method: "GET"
    })
}

// da URL
async function getMeseURL() {
    const urlParam = new URLSearchParams(window.location.search)
    return urlParam.get('mese')
}


async function getAnnoURL() {
    const urlParam = new URLSearchParams(window.location.search)
    return urlParam.get('anno')
}

// dall hover sull td
async function getGiorno(mese, anno) {

    const Alltd = document.querySelectorAll("td")

    Alltd.forEach(td => {

        td.addEventListener("mouseenter", async (event) => {
            const selectedTd = event.currentTarget;
            let giorno = selectedTd.innerText;
            console.log(giorno, "giorno")
            console.log(mese, "mese")
            console.log(anno, "anno")

            if (giorno === "") {
                console.log("la casella selezionata è vuota");
                return;
            }

            await callServletAppuntamenti(anno, mese, giorno);

        })
    })

}