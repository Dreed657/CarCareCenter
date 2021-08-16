import { config } from "../utils/ConfigUtil";

import axios, { AxiosResponse } from 'axios';
import CarDto from "../models/CarDto";

class CarService {
    getAll(): Promise<AxiosResponse> {
        return axios.get<CarDto[]>(`${config.API_URL}/cars/`);
    }

    getById(id: string): Promise<AxiosResponse> {
        return axios.get<CarDto>(`${config.API_URL}/cars/${id}/`);
    }
}

export default new CarService();
