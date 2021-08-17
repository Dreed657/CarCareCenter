"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var axios_1 = require("axios");
var fs = require("fs");
var cheerio = require("cheerio");
console.log("Started?!");
var url = "https://www.enchantedlearning.com/wordlist/carparts.shtml";
var AxiosInstance = axios_1.default.create();
var wordlist = [];
AxiosInstance.get(url)
    .then(function (res) {
    var html = res.data;
    var $ = cheerio.load(html);
    var wordlistItems = $(".wordlist-wrapper > .wordlist-section > .wordlist-item");
    wordlistItems.each(function (i, elem) {
        wordlist.push($(elem).text());
    });
})
    .then(function () {
    console.log("Words collected: ", wordlist.length);
    fs.writeFile('./output/carpars-' + new Date().getTime() + '.txt', JSON.stringify(wordlist), console.error);
})
    .catch(console.error);
//# sourceMappingURL=index.js.map