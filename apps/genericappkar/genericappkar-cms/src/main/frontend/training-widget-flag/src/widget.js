import React from "react";
import { withRootHoc } from "omni-widget";
import { getRandomCountry } from "./services/dataService";
import { useSelector } from "react-redux";

function Widget() {
  const { I18nProvider, HttpClient } = useSelector((store) => {
    return {
      I18nProvider: store.widget.services.I18nProvider,
      HttpClient: store.widget.services.HttpClient
    };
  });

  const [currentCountry, setCurrentCountry] = React.useState(null);
  React.useEffect(() => {
    setInterval(() => {
      getRandomCountry(HttpClient).then((countryInfo) => {
        setCurrentCountry(countryInfo);
      });
    }, 5000);
  }, []);
  return (
    <div style={ROOT_STYLE}>
      <span>{I18nProvider.Labeli18n("world_flag")}</span>
      {currentCountry ? (
        <>
          <img style={FLAG_IMAGE_STYLE} src={currentCountry.urlFlag} />
          <span>{currentCountry.name}</span>
        </>
      ) : (
        I18nProvider.Labeli18n("loading")
      )}
    </div>
  );
}

export default withRootHoc(Widget);

const ROOT_STYLE = {
  display: "flex",
  height: "100%",
  alignItems: "center",
  justifyContent: "space-around",
  position: "relative",
  flexDirection: "column",
  padding: 10
};

const FLAG_IMAGE_STYLE = {
  width: "100px",
  height: "auto"
};
