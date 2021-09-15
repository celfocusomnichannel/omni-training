import COUNTRY_CODE from "./countryCodes";

export const getCountryFlag = function (countryCode) {
  return `https://www.countryflags.io/${countryCode}/flat/64.png`;
};

export const getRandomCountry = function () {
  const randomIndex = Math.floor(Math.random() * COUNTRY_CODE.length - 1);
  return COUNTRY_CODE[randomIndex];
};
