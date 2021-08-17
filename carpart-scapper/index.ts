import axios from "axios";
import * as fs from 'fs';
import * as cheerio from "cheerio";

console.log("Started?!");

const url = "https://www.enchantedlearning.com/wordlist/carparts.shtml";
const AxiosInstance = axios.create();
const wordlist: string[] = [];

AxiosInstance.get(url)
  .then((res) => {
    const html = res.data;
    const $ = cheerio.load(html);
    const wordlistItems = $(
      ".wordlist-wrapper > .wordlist-section > .wordlist-item"
    );

    wordlistItems.each((i, elem) => {
      wordlist.push($(elem).text());
    });
  })
  .then(() => {
      console.log("Words collected: ", wordlist.length)
      fs.writeFile('./output/carpars-'+ new Date().getTime() + '.txt', JSON.stringify(wordlist), console.error);
  })
  .catch(console.error);

