import React from "react";
import { withRootHoc } from "omni-widget";
import { getRandomCountry, getCountryFlag } from "./services/dataService";
import COUNTRY_CODE from "./services/countryCodes";
import { useSelector } from "react-redux";

function Widget() {
  // const I18nProvider = useSelector(
  //   (store) => store.journey.services.I18nProvider
  // );

  const [currentCountry, setCurrentCountry] = React.useState(COUNTRY_CODE[180]);
  const [countryNameVisible, setCountryNameVisibility] = React.useState(false);
  React.useEffect(() => {
    setInterval(() => {
      setCurrentCountry(getRandomCountry());
    }, 5000);
  }, []);
  return (
    <div
      style={{
        display: "flex",
        height: "100%",
        alignItems: "center",
        justifyContent: "center",
        position: "relative",
        flexDirection: "column"
      }}
    >
      <span>{"world_flag"}</span>
      <img
        src={`https://www.countryflags.io/${currentCountry.Code}/flat/64.png`}
        onMouseOver={() => {
          !countryNameVisible && setCountryNameVisibility(true);
        }}
        onMouseOut={() => {
          countryNameVisible && setCountryNameVisibility(false);
        }}
      />
      {countryNameVisible && <span>{currentCountry.Name}</span>}
    </div>
  );
}

export default withRootHoc(Widget);
