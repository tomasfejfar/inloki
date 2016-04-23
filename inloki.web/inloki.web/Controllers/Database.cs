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
		public Database()
		{
			Beacons = new List<Beacon>();
			Paths = new List<Path>();
		}

		public List<Beacon> Beacons { get; set; }
		public List<Path> Paths { get; private set; }
	}
}