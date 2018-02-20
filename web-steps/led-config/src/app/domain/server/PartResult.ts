import {deserialize, deserializeAs} from "cerialize";
import {Part} from "./Part";
export class PartResult {

  @deserializeAs(Part)
  part: Part;

  @deserialize
  result: string;

}
