import React from "react";
import { withRootHoc } from "omni-widget";
import { getRandomCountry, getCountryFlag } from "./services/dataService";
import { useSelector } from "react-redux";

function Widget() {
  const I18nProvider = useSelector(
    (store) => store.widget.services.I18nProvider
  );

  const [currentCountry, setCurrentCountry] = React.useState(
    getRandomCountry()
  );
  React.useEffect(() => {
    setInterval(() => {
      setCurrentCountry(getRandomCountry());
    }, 5000);
  }, []);
  return (
    <div style={ROOT_STYLE}>
      <span>{I18nProvider.Labeli18n("world_flag")}</span>
      <img src={getCountryFlag(currentCountry.Code)} />
      <span>{currentCountry.Name}</span>
    </div>
  );
}

export default withRootHoc(Widget);

const ROOT_STYLE = {
  display: "flex",
  height: "100%",
  alignItems: "center",
  justifyContent: "center",
  position: "relative",
  flexDirection: "column"
};
