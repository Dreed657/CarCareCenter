import ItemDto from "./ItemDto";
import {Status} from "./enums/Status";

export default interface RepairDto {
    id: number;
    mileage: number;
    status: Status;
    createdAt: Date;
    totalPrice: number;
    items: ItemDto[];
}
