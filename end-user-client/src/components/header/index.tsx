import React from 'react';
import { AppBar, BottomNavigation, BottomNavigationAction } from '@material-ui/core';
import { useHistory } from 'react-router-dom';

import HomeIcon from '@material-ui/icons/Home';
import AssessmentIcon from '@material-ui/icons/Assessment';

const PageLayout = (props: any) => {
    const [value, setValue] = React.useState(0);
    const history = useHistory();

    const handleChange = (event: any, newValue: any) => {
        setValue(newValue);
        switch (newValue) {
            case 0:
                history.push('/');
                break;
            case 1:
                history.push('/stats');
                break;
        }
    };

    return (
        <AppBar elevation={1} position='static' color='default'>
            <BottomNavigation
                value={value}
                onChange={handleChange}
                showLabels
            >
                <BottomNavigationAction label='Home' icon={<HomeIcon />} />
                <BottomNavigationAction label='Stats' icon={<AssessmentIcon />} />
            </BottomNavigation>
        </AppBar>
    );
};

export default PageLayout;
