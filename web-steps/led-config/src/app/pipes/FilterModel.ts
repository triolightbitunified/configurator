import {Pipe, PipeTransform} from '@angular/core';
import {Model} from "../domain/Model";
import {DisplayRelation} from "../domain/internal/DisplayRelation";
@Pipe({
  name: 'filterModel',
  pure: false
})
export class FilterModel implements PipeTransform {
  transform(items: Model[], filter: DisplayRelation): any {
    if (!items || !filter) {
      return items;
    }

    // filter items array, items which match and return true will be kept, false will be filtered out
    return items.filter(item => true);
  }
}
