const styles = (theme) => ({
    root: {
        marginTop: '3vh',
        maxWidth: 400,
        flexGrow: 1,
        textAlign: 'center',
        display: 'block',
        marginRight: 'auto',
        marginLeft: 'auto'
    },
    header: {
        display: 'flex',
        alignItems: 'center',
        height: 50,
        paddingLeft: '4px',
        backgroundColor: theme.palette.background.default
    },
    img: {
        height: 255,
        maxWidth: 400,
        overflow: 'hidden',
        display: 'block',
        width: '100%'
    }
});

export default styles;
