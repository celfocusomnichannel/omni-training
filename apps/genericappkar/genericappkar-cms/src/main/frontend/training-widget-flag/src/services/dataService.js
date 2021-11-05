import COUNTRY_CODE from "./countryCodes";

export const getFlagInfo = function (HttpClient, countryCode) {
  let config = {
    method: "get",
    url: "/bin/mvc.do/genericappkar/v1/app/" + countryCode
  };
  return new Promise((resolve, reject) => {
    HttpClient.request(config)
      .then((res) => {
        const response = res.data;
        console.log(response, "response");
        if (!!response) {
          resolve(response);
          return;
        }
        reject("not found");
      })
      .catch(function (error) {
        console.log(error, "error");
        reject(error);
      });
  });
};

export const getRandomCountry = function (HttpClient) {
  const randomIndex = Math.floor(Math.random() * COUNTRY_CODE.length - 1);
  const country = COUNTRY_CODE[randomIndex];
  return getFlagInfo(HttpClient, country.Code).then((data) => {
    return { ...data, ...country };
  });
};
