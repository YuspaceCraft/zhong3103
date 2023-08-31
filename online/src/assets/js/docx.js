import docxtemplater from 'docxtemplater';
import PizZip from 'pizzip';
import { saveAs } from 'file-saver';

/**
 * 导出docx
 * @param {String} tempDocxPath 模板文件路径
 * @param {Object} data 文件中传入的数据
 * @param {String} fileName 导出文件名称
 */
export const exportDocx = (file, data, fileName) => {
    const reader = new FileReader();
  
    reader.onload = function (event) {
      const arrayBuffer = event.target.result;
  
      let zip = new PizZip(arrayBuffer);
      let doc = new docxtemplater().loadZip(zip);
      doc.setData(data);
      try {
        doc.render();
      } catch (error) {
        console.log(error);
        throw error;
      }
      const out = doc.getZip().generate({
        type: 'blob',
        mimeType: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      });
      saveAs(out, fileName);
    };
  
    reader.readAsArrayBuffer(new Blob([file]));
  };
  
  