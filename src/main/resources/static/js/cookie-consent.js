(function () {
    document.cookie = "auraCookieConsent=; path=/; max-age=0; SameSite=Lax";

    var consentName = "auraCookieSessionConsent";
    var accepted = document.cookie.split(";").some(function (cookie) {
        return cookie.trim() === consentName + "=accepted";
    });

    if (accepted) {
        return;
    }

    document.body.classList.add("cookie-locked");

    var overlay = document.createElement("div");
    overlay.className = "cookie-consent";
    overlay.innerHTML =
        '<div class="cookie-consent-box">' +
        '<h2>This website uses cookies</h2>' +
        '<p>You must accept all cookies to use the Aura Tech Stores web app.</p>' +
        '<button type="button" class="btn" id="acceptAllCookies">Accept all</button>' +
        '</div>';

    document.body.appendChild(overlay);

    document.getElementById("acceptAllCookies").addEventListener("click", function () {
        document.cookie = consentName + "=accepted; path=/; SameSite=Lax";
        overlay.remove();
        document.body.classList.remove("cookie-locked");
    });
})();
