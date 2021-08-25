import React, { useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';

// @ts-ignore
import ReactTimeAgo from 'react-time-ago';

import PageLayout from '../../components/page-layout';
import CarService from '../../services/CarService';
import CarDto from '../../models/CarDto';

import { Divider, Fab, Grid, Typography } from '@material-ui/core';
import EditIcon from '@material-ui/icons/Edit';
import AddIcon from '@material-ui/icons/Add';

import Loader from '../../components/loader';
import RepairsTable from './tables/repair-table';

interface paramsQuery {
    id: string;
}

const CarDetailsPage = () => {
    const [car, setCar] = useState<CarDto>();

    const params = useParams<paramsQuery>();
    const history = useHistory();

    useEffect(() => {
        setCar(undefined);
        CarService.getById(params.id).then((res) => {
            setCar(res.data);
        }).catch(e => {
            if (e.response.status === 404) {
                history.push('/error');
            }
        });
    }, [params, history]);

    return (
        <>
            <PageLayout>
                {car ? (
                    <>
                        <h1 style={{ 'textAlign': 'center' }}>Single car details page!</h1>
                        <Grid container spacing={6}>
                            <Grid item xs={6}>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Id: {car?.id}
                                </Typography>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Vin: {car?.vin}
                                </Typography>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Model: {car?.model}
                                </Typography>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Manufacturer: {car?.manufacturer}
                                </Typography>
                            </Grid>
                            <Grid item xs={6}>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Engine type: {car?.type}
                                </Typography>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Year: {car?.year}
                                </Typography>
                                <Typography variant='body1' align={'center'} gutterBottom>
                                    Added: <ReactTimeAgo date={car?.createdAt} locale='en' />
                                </Typography>
                            </Grid>
                        </Grid>
                        <Divider />
                        <RepairsTable carId={car.id} />
                    </>
                ) : (
                    <Loader />
                )}
            </PageLayout>
            <Fab color='primary' aria-label='add'>
                <AddIcon />
            </Fab>
            <Fab color='secondary' aria-label='edit'>
                <EditIcon />
            </Fab>
        </>
    );
};

export default CarDetailsPage;
