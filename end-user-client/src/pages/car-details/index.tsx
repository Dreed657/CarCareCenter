import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

// @ts-ignore
import ReactTimeAgo from 'react-time-ago'

import AutorenewIcon from '@material-ui/icons/Autorenew';
import DoneIcon from '@material-ui/icons/Done';

import PageLayout from "../../components/page-layout";
import CarService from "../../services/CarService";
import CarDto from "../../models/CarDto";

import {
    Chip,
    Divider,
    Grid,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@material-ui/core";
import Loader from "../../components/loader";
import {Status} from "../../models/enums/Status";

interface paramsQuery {
    id: string
}

const CarDetailsPage = () => {
    const [car, setCar] = useState<CarDto>();

    const params = useParams<paramsQuery>();

    useEffect(() => {
        CarService.getById(params.id).then((res) => {
            setCar(res.data);
        });
    }, [params]);

    return (
        <PageLayout>
            {car ? (
                <>
                    <h1 style={{'textAlign': 'center'}}>Single car details page!</h1>
                    <Grid container spacing={6} alignContent={"center"}>
                        <Grid item xs={2}>
                            <p>Id: </p>
                            {car?.id}
                            <p>Vin: </p>
                            {car?.vin}
                            <p>Model: </p>
                            {car?.model}
                            <p>Manufacturer: </p>
                            {car?.manufacturer}
                            <p>Engine type: </p>
                            {car?.type}
                            <p>Year: </p>
                            {car?.year}
                            <p>Time ago: </p>
                            <ReactTimeAgo date={car?.createdAt} locale="en"/>
                            <Divider/>
                        </Grid>
                        <Divider orientation="vertical" flexItem/>
                        <Grid item xs={9}>
                            <TableContainer component={Paper}>
                                <Table aria-label="custom pagination table">
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Id</TableCell>
                                            <TableCell>Mileage</TableCell>
                                            <TableCell>Added ago</TableCell>
                                            <TableCell>Status</TableCell>
                                            <TableCell>Items</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {car.repairments.map((repair) => (
                                            <TableRow key={repair.id} hover>
                                                <TableCell>{repair.id}</TableCell>
                                                <TableCell>{repair.mileage}</TableCell>
                                                <TableCell><ReactTimeAgo date={repair?.createdAt}
                                                                         locale="en"/></TableCell>
                                                <TableCell>
                                                    {repair.status.toString().toLowerCase() === Status.PROGRESS.toString().toLowerCase() ? (
                                                        <Chip label="In-Progress" variant="outlined" color="primary"
                                                              size="small" icon={<AutorenewIcon/>}/>
                                                    ) : (
                                                        <Chip label="Finished" variant="outlined" color="secondary"
                                                              size="small" icon={<DoneIcon/>}/>
                                                    )}
                                                </TableCell>
                                                <TableCell>{repair.items.length}</TableCell>
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Grid>
                    </Grid>
                </>
            ) : (
                <Loader/>
            )}
        </PageLayout>
    );
};

export default CarDetailsPage;
