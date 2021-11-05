export function styles(theme) {
  return {
    artistInfoRoot: {
      padding: "10px",
      display: "flex",
      border: `solid 1px ${theme.palette.primary.main}`,
      position: "relative",
      height: "auto",
      width: "auto",
      opacity: "0.8",
      backgroundColor:
        theme.palette.type === "dark"
          ? theme.palette.background.default
          : theme.palette.text.primary, ///#333333
      color: theme.palette.common.white
    },
    artWork: {
      width: 100,
      height: "auto"
    },
    trackInfo: {
      display: "flex",
      flexDirection: "column",
      marginLeft: 15
    },
    artistName: { fontSize: "1.5rem" },
    collectionName: { fontSize: "1rem", marginTop: "5px" }
  };
}
