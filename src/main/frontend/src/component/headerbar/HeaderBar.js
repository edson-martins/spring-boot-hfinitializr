import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import BugReportIcon from '@material-ui/icons/BugReport';

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    title: {
      flexGrow: 1,
    }
}));

const HeaderBar = () => {

    const classes = useStyles();

    return (
        <div>
            <CssBaseline />
            <AppBar>
                <Toolbar className={classes.toolbar}>
                    <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                        HFInitializr
                    </Typography>
                    <IconButton color="inherit">
                        <Badge color="secondary">
                            <BugReportIcon />
                        </Badge>
                    </IconButton>
                </Toolbar>
            </AppBar>
        </div>
    )
}

export default HeaderBar;