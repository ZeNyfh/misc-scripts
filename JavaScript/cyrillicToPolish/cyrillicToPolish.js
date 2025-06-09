// ==UserScript==
// @name         Cyrillic to Polish Transliteration
// @namespace    http://tampermonkey.net/
// @version      1.6
// @description  Transliterates Cyrillic characters on web pages to their Polish equivalents
// @author       ZeNyfh
// @match        *://*.youtube.com/*
// @match        *://*.genius.com/*
// @match        *://*.lyricstranslate.com/*
// @match        *://*.onlinenotepad.org/*
// @grant        none
// ==/UserScript==

(function() {
    'use strict';

    const rusToPolMap = {
        "Что": "Szto", "Щее": "Śije", "Еще": "Eśo", "его": "ewo", "Ае": "Aje", "Ое": "Oje", "Яе": "Jaje", "аа": "aja", "оо": "a", "ее": "jeje", "Сс": " s", "Дь": "Dź", "Зь": "Ź", "Нь": "Ń", "Пь": "Pj", "Рь": "R", "Сь": "Ś", "Ть": "t", "А": "A", "Б": "B", "В": "W", "Г": "G", "Д": "D", "Е": "E", "Ё": "Jo", "Ѣ": "Lje",
        "Ж": "Ż", "З": "Z", "И": "I", "Й": "J", "К": "K", "Л": "L", "Ў": "L", "М": "M",
        "Н": "N", "О": "O", "П": "P", "Р": "R", "С": "S", "Т": "T", "У": "U",
        "Ф": "F", "Х": "Ch", "Ц": "C", "Ч": "Cz", "Ш": "Sz", "Щ": "Ś",
        "Ъ": "", "Ы": "Y", "Ь": "", "Э": "E", "Ю": "Ju", "Я": "Ja", "Ѫ": "Ia", "Ћ": "Cz", "Ѧ": "Ę", "Ї": "Ji", "Є": "Ie", "І": "I"
    };

    function toPol(text) {
        for (const [rusChar, polChar] of Object.entries(rusToPolMap)) {
            const regexUpper = new RegExp(rusChar, 'g');
            const regexLower = new RegExp(rusChar.toLowerCase(), 'g');
            text = text.replace(regexUpper, polChar);
            text = text.replace(regexLower, polChar.toLowerCase());
            text = text.replace("ij", "yj");
        }
        return text;
    }

    function transliterateTextNodes(node) {
        if (node.nodeType === Node.TEXT_NODE) {
            node.nodeValue = toPol(node.nodeValue);
        } else {
            for (let child = node.firstChild; child; child = child.nextSibling) {
                transliterateTextNodes(child);
            }
        }
    }

    const observer = new MutationObserver((mutations) => {
        mutations.forEach((mutation) => {
            if (mutation.type === 'childList') {
                mutation.addedNodes.forEach((node) => {
                    if (node.nodeType === Node.TEXT_NODE) {
                        node.nodeValue = toPol(node.nodeValue);
                    } else {
                        transliterateTextNodes(node);
                    }
                });
            }
        });
    });

    observer.observe(document.body, { childList: true, subtree: true });
    transliterateTextNodes(document.body);
})();

