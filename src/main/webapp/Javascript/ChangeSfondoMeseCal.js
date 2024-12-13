document.addEventListener("DOMContentLoaded", async function () {
    // ritorna il mese in formato '10' ...
    let mese = getMeseURL();
    const meseLett = getMeseLetterale(mese);
    const URLimg = await getImmaginePexels(meseLett);
    setImgAsSfondo(URLimg);
});

// prelevo il mese sotto forma di numero
function getMeseURL() {
    const urlParam = new URLSearchParams(window.location.search)
    return urlParam.get('mese')
}

function getMeseLetterale(mese) {
    const arrMesi = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
    ];

    return arrMesi[mese - 1]
}

async function getImmaginePexels(meseLett) {
    try {
        const res = await fetch(`https://api.pexels.com/v1/search?query=${meseLett}`, {
            method: "GET",
            headers: {
                Authorization: "7Ye7PHnNDdVmd43T5cthTwaF0I2AipmjtizxjFtVcXnzQIgCqJYlTLXP",
                "Content-Type": "application/json",
            },
        });

        if (!res.ok) {

            if (res.status > 400 && res.status < 500) {
                return Error("errore lato client");
            }

            if (res.status > 500) {
                return Error("errore server.");
            }

            return Error("errore generico.");

        } else {
            const weHavedata = await res.json();
            const myPhoto = weHavedata.photos[Math.floor(Math.random() * weHavedata.photos.length)].src.large2x;
            console.log(myPhoto);
            return myPhoto;
        }
    } catch (err) {
        console.log("problemino: " + err);
    }
}


function setImgAsSfondo(url) {
    document.body.style.backgroundImage = `url(${url})`
}