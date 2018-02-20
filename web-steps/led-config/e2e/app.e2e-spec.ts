import { LedConfigPage } from './app.po';

describe('led-config App', () => {
  let page: LedConfigPage;

  beforeEach(() => {
    page = new LedConfigPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
