import { config } from '../utils/ConfigUtil';

import axios, { AxiosResponse } from 'axios';
import CarDto from '../models/CarDto';
import CarInputDto from '../models/CarInputDto';

class CarService {
    getAll(page: number, size: number): Promise<AxiosResponse> {
        return axios.get<CarDto[]>(`${config.API_URL}/cars?sortBy=createdAt&page=${page}&size=${size}`);
    }

    getById(id: string): Promise<AxiosResponse> {
        return axios.get<CarDto>(`${config.API_URL}/cars/${id}`);
    }

    getCount(): Promise<AxiosResponse> {
        return axios.get<number>(`${(config.API_URL)}/cars/count`);
    }

    createCar(data: CarInputDto): Promise<AxiosResponse> {
        return axios.post(`${config.API_URL}/cars/`, data);
    }
}

export default new CarService();
