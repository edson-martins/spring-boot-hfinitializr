import React, { useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import CreateIcon from '@material-ui/icons/Create';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';

import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { WithContext as ReactTags } from 'react-tag-input';
import './css/ReactTags.css';
import GenerateFixAction from '../action/GenerateFixAction';

const KeyCodes = {
    comma: 188,
    enter: 13,
};

const delimiters = [KeyCodes.comma, KeyCodes.enter];

const useStyles = makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(2),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    selector: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(3),
    },
    content: {
        marginTop: theme.spacing(3),
    },
    child: {
        marginTop: theme.spacing(3),
        width: '99.4%',
    },
    textArea: {
        marginTop: theme.spacing(3),
        width: '100%',
    },
    logo: {
        color:'red'
    }
}));

const Forms = () => {

    const classes = useStyles();
    const [check, fixType]  = useState({ checked: 'bundle' });
    const [form,  setForm]  = useState({ bugId: '', title: '', readme: ''});
    const [state, setBugs]  = useState({ bugs: []});


    const fixTypeSelection = (event) => {
        fixType({ checked: event.target.value });
    }

    const handleDelete = (i) => {
        const bug = state.bugs;
        setBugs({ bugs: bug.filter((bug, index) => index !== i) });
    }

    const handleAddition = (bug) => {
        setBugs(state => ({ bugs: [...state.bugs, bug] }));
    }

    const handleDrag = (bug, currPos, newPos) => {
        const bugs = [...state.bugs];
        const newBugs = bugs.slice();

        newBugs.splice(currPos, 1);
        newBugs.splice(newPos, 0, bug);

        // re-render
        setBugs({ bugs: newBugs });
    }

    const onChangeFormHandler = (event) => {
        const value = event.target.value;
        setForm({
            ...form,
            [event.target.name]: value
          });
      };
    

    return (
        <Container component="main" maxWidth="lg">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <CreateIcon />
                </Avatar>
                <Typography component="h1" variant="h5">
                    <strong><span className={classes.logo}>HF</span>Initializr</strong>
                </Typography>
                <form className={classes.form} noValidate>
                    <div className={classes.selector}>
                        <RadioGroup row aria-label="position" name="position" defaultValue="bundle">
                            <FormControlLabel
                                value="bundle"
                                control={<Radio color="primary" />}
                                label="Bundle"
                                labelPlacement="end"
                                onChange={fixTypeSelection}
                            />
                            <FormControlLabel
                                value="hotfix"
                                control={<Radio color="primary" />}
                                label="Hotfix"
                                labelPlacement="end"
                                onChange={fixTypeSelection}
                            />
                        </RadioGroup>
                    </div>
                    <div className={classes.content}></div>
                    <Grid container spacing={4}>
                        <Grid item xs={8} sm={3}>
                            <TextField
                                autoComplete="fbug"
                                name="bugId"
                                variant="outlined"
                                required
                                fullWidth
                                type="number"
                                id="bugId"
                                label="BugId"
                                value={form.bugId}
                                onChange={onChangeFormHandler}
                                autoFocus
                            />
                        </Grid>
                        <Grid item xs={8} sm={9}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                id="title"
                                label="Title"
                                name="title"
                                value={form.title}
                                onChange={onChangeFormHandler}
                                autoComplete="ltitle"
                            />
                        </Grid>
                    </Grid>
                    {(check.checked === 'bundle') ?
                        <div className={classes.child}>
                            <ReactTags tags={state.bugs}
                                handleDelete={handleDelete}
                                handleAddition={handleAddition}
                                handleDrag={handleDrag}
                                labelField={'title'}
                                placeholder="Enter the child Bug number(s)"
                                delimiters={delimiters} />
                        </div> : null
                    }
                    <div className={classes.textArea}>
                        <Grid container spacing={4}>
                            <Grid item xs={12}>
                                <TextField
                                    id="outlined-multiline-static"
                                    label="Notes to the README file"
                                    required
                                    multiline
                                    fullWidth
                                    rows={6}
                                    name="readme"
                                    autoComplete="lreadme"
                                    variant="outlined"
                                    value={form.readme}
                                    onChange={onChangeFormHandler}
                                />
                            </Grid>
                        </Grid>
                    </div>
                    <GenerateFixAction forms={form} state={state} type={check}/>
                </form>
            </div>
        </Container>

    );
}

export default Forms