import { Metric } from "web-vitals";
import { Status } from "./enums/Status";

export default interface RepairDto {
  id: number;
  description: string;
  quantity: number;
  metric: Metric;
}
