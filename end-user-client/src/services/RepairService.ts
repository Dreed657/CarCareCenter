import axios, { AxiosResponse } from 'axios';

import { config } from '../utils/ConfigUtil';

import RepairDto from '../models/RepairDto';

class RepairService {
    getAll(page: number, size: number): Promise<AxiosResponse> {
        return axios.get<RepairDto[]>(`${config.API_URL}/repairs?page=${page}&size=${size}`);
    }

    getById(id: string): Promise<AxiosResponse> {
        return axios.get<RepairDto>(`${config.API_URL}/repairs/${id}`);
    }

    getByCarId(carId: number): Promise<AxiosResponse> {
        return axios.get<RepairDto[]>(`${config.API_URL}/repairs/car/${carId}`);
    }

    getCount(): Promise<AxiosResponse> {
        return axios.get<number>(`${(config.API_URL)}/repairs/count`);
    }
}

export default new RepairService();