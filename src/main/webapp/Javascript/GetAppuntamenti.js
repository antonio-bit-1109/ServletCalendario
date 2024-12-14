document.addEventListener("DOMContentLoaded", async function () {

    const mese = await getMeseURL();
    const anno = await getAnnoURL();
    await getGiorno(mese, anno);

})

// fetch che chiamerà il controller/servlet -- ritornerà dei dati -- che ci faccio con quei dati ??
async function callServletAppuntamenti(anno, mese, giorno) {
    try {
        const res = await fetch(`http://localhost:8080/CalendarioServlet/AppuntamentiFetch?anno=${anno}&mese=${mese}&giorno=${giorno}`, {
            method: "GET"
        })

        if (!res.ok) {

            if (res.status >= 400 && res.status <= 499) {
                return Error("errore client.")
            }

            if (res.status >= 500 && res.status <= 599) {
                return Error("errore server.")
            }

            return Error("errore generico");

        } else {

            const JsonData = await res.json();
            console.log(JsonData);


            // prendo elementi del dom e spalmo dati ricevuti nulla tabella
            ShowData_Table(JsonData);


        }
    } catch (ex) {
        console.log("errore: " + ex)
    }

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

function ShowData_Table(jsonData) {
    const tHead = document.getElementById("thead-temp-table");
    tHead.innerHTML = '';

    const tBody = document.getElementById("tbody-temp-table");
    tBody.innerHTML = '';

    if (jsonData.length === 0) {
        return;
    }

    console.log(jsonData);


    const headerRow = document.createElement("tr");
    const headers = ["data", "ora", "descrizione"];
    headers.forEach(headerText => {
        const th = document.createElement("th");
        th.innerText = headerText;
        th.classList.add("p1-r")
        headerRow.appendChild(th);
    });
    tHead.appendChild(headerRow);


    jsonData.forEach(obj => {
        const row = document.createElement("tr");

        headers.forEach(key => {
            const td = document.createElement("td");
            td.innerText = obj[key];
            td.classList.add("p1-r")
            row.appendChild(td);
        });

        tBody.appendChild(row);
    });
}