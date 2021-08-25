import axios, { AxiosResponse } from 'axios';
import RepairDto from '../models/RepairDto';
import { config } from '../utils/ConfigUtil';
import ItemDto from '../models/ItemDto';

class ItemService {
    getAll(page: number, size: number): Promise<AxiosResponse> {
        return axios.get<RepairDto[]>(`${config.API_URL}/items?page=${page}&size=${size}`);
    }

    getById(id: string): Promise<AxiosResponse> {
        return axios.get<RepairDto>(`${config.API_URL}/items/${id}`);
    }

    createItem(data: ItemDto): Promise<AxiosResponse> {
        return axios.post(`${config.API_URL}/items/`, data);
    }

    updateItem(data: ItemDto): Promise<AxiosResponse> {
        return axios.put(`${config.API_URL}/items/${data.id}`, data);
    }

    deleteItem(itemId: number): Promise<AxiosResponse> {
        return axios.delete(`${config.API_URL}/items/${itemId}`);
    }

    getCount(): Promise<AxiosResponse> {
        return axios.get<number>(`${(config.API_URL)}/items/count`);
    }
}

export default new ItemService();