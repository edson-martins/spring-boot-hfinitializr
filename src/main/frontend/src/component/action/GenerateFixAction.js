import React from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    submit: {
        margin: theme.spacing(3, 0, 2),
    }
}));

const GenerateFixAction = (props) => {

    const classes = useStyles();

    const type = props.type.checked;
    const notes = props.forms.readme.split('\n');
    const message = JSON.stringify({
        "bugId": props.forms.bugId,
        "title": props.forms.title,
        "notes": notes.map((note, index) => { return { note } }),
        "childBugs": props.state.bugs.map((bug) => { return { bugId: bug.id } })
    });

    const download = (data) => {
        let filename;
        window.URL = window.URL || window.webkitURL;
        const blob = new Blob([data], {type: 'application/zip'});
        
        if ((props.forms.bugId.trim() === "") || (props.forms.bugId.trim().length === 0)) {
            filename = 'file.zip';
        } else {
            filename = props.forms.bugId + '.zip';
        }

        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);

        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
        link.remove();
        window.location.reload(false);
    };

    const packageGenerationService = () => {

        const msg = message;
        const url = '/' + type;
        
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
            },
            responseType: 'blob',
            body: msg
        }).then(response => {
            if (response.ok) {
                return response.blob();
            }
        }).then(blob => {
            download(blob);

        }).catch(error => console.error(error));
    };

    return (
        <Button
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={packageGenerationService}>
            Generate
        </Button>
    );

}

export default GenerateFixAction;