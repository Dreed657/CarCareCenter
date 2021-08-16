import {Metric} from "./enums/Metric";

export default interface ItemDto {
  id: number;
  description: string;
  quantity: number;
  metric: Metric;
}
