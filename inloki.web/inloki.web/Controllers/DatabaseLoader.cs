using inloki.web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.Azure;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Newtonsoft.Json;

namespace inloki.web.Controllers
{
	public partial class Database
	{
		static Database()
		{

		}

		public static void Modify(Action<Database> action)
		{
			var database = Database.Load();

			action(database);

			Database.Save(database);
		}

		public static void Query(Action<Database> action)
		{
			var database = Database.Load();

			action(database);
		}

		public static Database Load()
		{
			var databaseBlob = GetDatabaseBlob();

			if (databaseBlob.Exists())
			{
				var serializedDatabase = databaseBlob.DownloadText();

				var database = JsonConvert.DeserializeObject<Database>(serializedDatabase);

				return database;
			}
			else
			{
				return new Database();
			}
		}

		private static void Save(Database database)
		{
			var databaseBlob = GetDatabaseBlob();

			databaseBlob.UploadText(JsonConvert.SerializeObject(database));
		}

		private static CloudBlockBlob GetDatabaseBlob()
		{
			var connectionString = CloudConfigurationManager.GetSetting("StorageConnectionString");
			var storageAccount = CloudStorageAccount.Parse(connectionString);
			var blobClient = storageAccount.CreateCloudBlobClient();
			var container = blobClient.GetContainerReference("inloki");

			container.CreateIfNotExists();

			var databaseBlob = container.GetBlockBlobReference("database");
			return databaseBlob;
		}
	}
}