import { config } from '../utils/ConfigUtil';

import axios, { AxiosResponse } from 'axios';
import CarDto from '../models/CarDto';

class CarService {
    getAll(page: number, size: number): Promise<AxiosResponse> {
        return axios.get<CarDto[]>(`${config.API_URL}/cars?page=${page}&size=${size}`);
    }

    getById(id: string): Promise<AxiosResponse> {
        return axios.get<CarDto>(`${config.API_URL}/cars/${id}`);
    }

    getCount(): Promise<AxiosResponse> {
        return axios.get<number>(`${(config.API_URL)}/cars/count`);
    }
}

export default new CarService();
