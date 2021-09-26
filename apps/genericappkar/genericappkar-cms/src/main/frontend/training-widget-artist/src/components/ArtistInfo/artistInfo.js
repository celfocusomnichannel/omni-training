import React from "react";
import PropTypes from "prop-types";
import { withStyles } from "@ui-lib/core/styles";
import Typography from "@ui-lib/core/Typography";
import { styles } from "./style";

const ArtistInfo = ({ artistInfo, classes }) => {
  return (
    <div className={classes.artistInfoRoot}>
      <img className={classes.artWork} src={artistInfo.artworkUrl100} />
      <div className={classes.trackInfo}>
        <Typography
          variant="subtitle2"
          component={"span"}
          classes={{ root: classes.artistName }}
        >
          {`${artistInfo.trackName} by ${artistInfo.artistName}`}
        </Typography>
        <Typography
          variant="subtitle2"
          component={"span"}
          classes={{ root: classes.collectionName }}
        >
          {`${artistInfo.collectionName} | ${artistInfo.primaryGenreName} | ${artistInfo.country}`}
        </Typography>
      </div>
    </div>
  );
};

ArtistInfo.propTypes = {
  artistInfo: PropTypes.shape({
    trackName: PropTypes.string,
    artistName: PropTypes.number,
    collectionName: PropTypes.number,
    primaryGenreName: PropTypes.number,
    artworkUrl100: PropTypes.string,
    country: PropTypes.string
  }),

  classes: PropTypes.object
};

export default withStyles(styles)(React.memo(ArtistInfo));
