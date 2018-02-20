export default class Utils {

  static pad(num:number, size:number): string {
    var s = num+"";
    while (s.length < size) s = "0" + s;
    return s;
  }


  public static padString(value: string, number: number):string {
    return this.pad(Number(value),number);
  }

}
