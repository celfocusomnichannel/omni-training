import React from "react";
import { withRootHoc } from "omni-widget";
import { getArtistSongs } from "./services/dataService";
import { useSelector } from "react-redux";

import { withStyles } from "@ui-lib/core/styles";
import { styles } from "./widgetStyle";

import Typography from "@ui-lib/core/Typography";
import TextField from "@ui-lib/core/TextField";
import Button from "@ui-lib/core/Button";

import ArtistInfo from "./components/ArtistInfo";

function Widget({ classes }) {
  const { I18nProvider, HttpClient } = useSelector((store) => {
    return {
      I18nProvider: store.widget.services.I18nProvider,
      HttpClient: store.widget.services.HttpClient
    };
  });

  const [artistName, setArtistName] = React.useState("Taylor Swift");
  const [limit, setLimit] = React.useState(20);
  const [songs, setSongs] = React.useState([]);
  const [loading, setLoading] = React.useState(false);

  const searchArtist = React.useCallback(() => {
    setLoading(true);
    getArtistSongs(HttpClient, artistName, limit)
      .then((songsList = []) => {
        setSongs(songsList);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
      });
  }, [artistName, limit, HttpClient]);

  React.useEffect(() => {
    searchArtist();
  }, []);
  return (
    <div className={classes.widgetRoot}>
      <Typography variant={"h2"}>
        {I18nProvider.Labeli18n("search_music_artist")}
      </Typography>
      <div className={classes.filterRoot}>
        <TextField
          fullWidth
          className={classes.inputField}
          id="search-artist"
          label={I18nProvider.Labeli18n("enter_music_artist")}
          value={artistName}
          onChange={(e) => {
            setArtistName(e.target.value);
          }}
        />
        <TextField
          fullWidth
          className={classes.inputField}
          id="search-limit"
          label={I18nProvider.Labeli18n("result_count")}
          value={limit}
          onChange={(e) => {
            setLimit(e.target.value);
          }}
        />
        <Button variant="outlined" color="primary" onClick={searchArtist}>
          {I18nProvider.Labeli18n("search")}
        </Button>
      </div>

      {!loading && songs && songs.length > 0 ? (
        <div className={classes.artistInfoContainer}>
          {songs.map((artistInfo) => {
            return (
              <a
                href={artistInfo.trackViewUrl}
                target="_blank"
                style={{ textDecoration: "none" }}
              >
                <ArtistInfo artistInfo={artistInfo} />
              </a>
            );
          })}
        </div>
      ) : loading ? (
        I18nProvider.Labeli18n("loading")
      ) : (
        I18nProvider.Labeli18n("artist_not_found")
      )}
    </div>
  );
}

export default withRootHoc(withStyles(styles)(Widget));
