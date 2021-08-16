import React, { useEffect, useState } from "react";

import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@material-ui/core";

import PageLayout from "../../components/page-layout";

import CarDto from "../../models/CarDto";
import CarService from "../../services/CarService";
import TimeUtils from "../../utils/TimeUtils";

const HomePage = () => {
  const [cars, setCars] = useState<CarDto[]>([]);

  useEffect(() => {
    CarService.getAll().then((res) => {
      setCars(res.data);
    });
  }, []);

  const handleClick = (data: any) => {
    console.log("Row num, ", data);
  };

  return (
    <PageLayout>
      <TableContainer component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Id</TableCell>
              <TableCell>Manufacturer</TableCell>
              <TableCell>Model</TableCell>
              <TableCell>VIN</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Year</TableCell>
              <TableCell>Added ago</TableCell>
              <TableCell>Repairments</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {cars.map((car) => (
              <TableRow key={car.id} hover onClick={() => handleClick(car.id)}>
                <TableCell>{car.id}</TableCell>
                <TableCell>{car.manufaturer}</TableCell>
                <TableCell>{car.model}</TableCell>
                <TableCell>{car.vin}</TableCell>
                <TableCell>{car.type}</TableCell>
                <TableCell>{car.year}</TableCell>
                <TableCell>{car.createdAt}</TableCell>
                <TableCell>{car.repairments.length}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </PageLayout>
  );
};

export default HomePage;
function useStyles() {
  throw new Error("Function not implemented.");
}
