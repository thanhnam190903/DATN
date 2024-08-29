<!-- Back to Top -->
$(document).ready(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn(300);
        } else {
            $('.back-to-top').fadeOut(300);
        }
    });
});

<!-- Topbar and navbar -->
window.addEventListener('scroll', function() {
    var topbar = document.getElementById('topbar');
    var navbar = document.getElementById('navbar');

    if (window.scrollY > 45) {
        topbar.style.top = '-45px';
        navbar.style.top = '0';
    }
    else {
        topbar.style.top = '0';
        navbar.style.top = '45px';
    }
});

<!-- btnVn and btnEN -->
document.getElementById('btnVN').addEventListener('click', function() {
    this.style.zIndex = '1';
    document.getElementById('btnEN').style.zIndex = '2';
});

document.getElementById('btnEN').addEventListener('click', function() {
    this.style.zIndex = '1';
    document.getElementById('btnVN').style.zIndex = '2';
});

function googleTranslateElementInit() {
  new google.translate.TranslateElement({pageLanguage: 'vi', includedLanguages: 'en,vi', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
}

function translateToEnglish() {
  var translateElement = document.querySelector('#google_translate_element select');
  if (translateElement) {
    translateElement.value = 'en';
    translateElement.dispatchEvent(new Event('change'));
  }
}
