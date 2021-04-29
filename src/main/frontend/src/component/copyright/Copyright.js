import React from 'react';
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import Box from '@material-ui/core/Box';

const Copyright = () => {
    return (
        <Box pt={4}>
        <Typography variant="body2" color="textSecondary" align="center">
          {'Copyright © '}
          <Link color="inherit" href="https://www.linkedin.com/in/edson-martins/" target="_blank">
            Edson Martins
          </Link>{' '}
          {new Date().getFullYear()}
          {'.'}
        </Typography>
        </Box>
      );
}

export default Copyright;