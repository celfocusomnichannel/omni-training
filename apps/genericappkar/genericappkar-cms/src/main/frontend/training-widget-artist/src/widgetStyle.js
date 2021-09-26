export function styles(theme) {
  return {
    widgetRoot: {
      display: "flex",
      height: "100%",
      alignItems: "start",
      justifyContent: "flex-start",
      position: "relative",
      flexDirection: "column",
      padding: 10,
      backgroundColor: theme.palette.background.default
    },
    filterRoot: {
      display: "flex",
      width: "100%",
      marginBottom: 10,
      alignItems: "flex-end"
    },
    inputField: {
      display: "block",
      position: "relative",
      backgroundColor: "inherit",
      width: "100%",
      margin: 10,
      padding: 0
    },
    artistInfoContainer: { width: "100%", height: "calc(100% - 170px)" }
  };
}
