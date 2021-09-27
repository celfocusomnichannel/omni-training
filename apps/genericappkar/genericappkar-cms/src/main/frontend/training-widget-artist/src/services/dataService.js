export const getArtistSongs = function (HttpClient, artistName, limit = 2) {
  let config = {
    method: "get",
    url:
      "/bin/mvc.do/genericappkar/v1/app/search?artistName=" +
      artistName +
      "&limit=" +
      limit
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
