import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";

// @ts-ignore
import ReactTimeAgo from 'react-time-ago'

import PageLayout from "../../components/page-layout";
import CarService from "../../services/CarService";
import CarDto from "../../models/CarDto";

import {
    Divider,
    Grid,
} from "@material-ui/core";

import Loader from "../../components/loader";
import RepairsTable from "./tables/repair-table";

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
                            <RepairsTable carId={car.id} />
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
